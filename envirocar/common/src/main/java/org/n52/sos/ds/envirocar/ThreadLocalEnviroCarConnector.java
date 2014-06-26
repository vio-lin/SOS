/**
 * Copyright (C) 2012-2014 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 as published
 * by the Free Software Foundation.
 *
 * If the program is linked with libraries which are licensed under one of
 * the following licenses, the combination of the program with the linked
 * library is not considered a "derivative work" of the program:
 *
 *     - Apache License, version 2.0
 *     - Apache Software License, version 1.0
 *     - GNU Lesser General Public License, version 3
 *     - Mozilla Public License, versions 1.0, 1.1 and 2.0
 *     - Common Development and Distribution License (CDDL), version 1.0
 *
 * Therefore the distribution of the program linked with libraries licensed
 * under the aforementioned licenses, is permitted by the copyright holders
 * if the distribution is compliant with both the GNU General Public
 * License version 2 and the aforementioned licenses.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 */
package org.n52.sos.ds.envirocar;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.n52.sos.ds.ConnectionProvider;
import org.n52.sos.ds.ConnectionProviderException;
import org.n52.sos.util.CollectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadLocalEnviroCarConnector {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadLocalEnviroCarConnector.class);

    private final ConnectionProvider connectionProvider;

    private final Lock lock = new ReentrantLock();

    private final Set<EnviroCarDaoFactory> createdEnviroCarDaoFactorys = CollectionHelper.synchronizedSet();

    private boolean closed = false;

    private ThreadLocal<EnviroCarDaoFactory> threadLocal = new ThreadLocal<EnviroCarDaoFactory>() {
        @Override
        protected EnviroCarDaoFactory initialValue() {
            try {
                return (EnviroCarDaoFactory) getConnectionProvider().getConnection();
            } catch (ConnectionProviderException cpe) {
                LOGGER.error("Error while getting initialValue for ThreadLocalEnviroCarDaoFactoryFactory!", cpe);
            }
            return null;
        }
    };

    public ThreadLocalEnviroCarConnector(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public EnviroCarDaoFactory getEnviroCarDaoFactory() {
        lock.lock();
        try {
            if (isClosed()) {
                throw new IllegalStateException("factory already closed");
            }
            EnviroCarDaoFactory s = this.threadLocal.get();
            getCreatedEnviroCarDaoFactorys().add(s);
            return s;
        } finally {
            lock.unlock();
        }
    }

    public void close() throws ConnectionProviderException {
        setClosed();
        returnEnviroCarDaoFactorys();
    }

    public ConnectionProvider getConnectionProvider() {
        return connectionProvider;
    }

    protected Set<EnviroCarDaoFactory> getCreatedEnviroCarDaoFactorys() {
        return createdEnviroCarDaoFactorys;
    }

    protected void setClosed() {
        lock.lock();
        try {
            closed = true;
        } finally {
            lock.unlock();
        }
    }

    protected boolean isClosed() {
        lock.lock();
        try {
            return closed;
        } finally {
            lock.unlock();
        }
    }

    protected void returnEnviroCarDaoFactorys() throws ConnectionProviderException {
        for (EnviroCarDaoFactory s : getCreatedEnviroCarDaoFactorys()) {
            getConnectionProvider().returnConnection(s);
        }
    }
}
