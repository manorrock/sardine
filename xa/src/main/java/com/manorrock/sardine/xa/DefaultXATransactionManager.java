/*
 * Copyright (c) 2002-2023 Manorrock.com. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, 
 *      this list of conditions and the following disclaimer.
 *   2. Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation
 *      and/or other materials provided with the distribution.
 *   3. Neither the name of the copyright holder nor the names of its 
 *      contributors may be used to endorse or promote products derived from this
 *      software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.manorrock.sardine.xa;

import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.InvalidTransactionException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.Status;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import jakarta.transaction.TransactionManager;
import java.util.HashMap;
import java.util.Map;

/**
 * The default TransactionManager.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
public class DefaultXATransactionManager implements TransactionManager {

    /**
     * Stores the timeout.
     */
    private int timeout;

    /**
     * Stores the thread-to-transaction map.
     */
    private final Map<Thread, Transaction> threadTransactionMap;

    /**
     * Constructor.
     */
    public DefaultXATransactionManager() {
        threadTransactionMap = new HashMap<>();
    }

    @Override
    public void begin() throws NotSupportedException, SystemException {
        DefaultXATransaction transaction = (DefaultXATransaction) getTransaction();
        if (transaction == null) {
            transaction = new DefaultXATransaction();
            transaction.setTransactionManager(this);
            transaction.setTimeout(timeout);
            Thread currentThread = Thread.currentThread();
            threadTransactionMap.put(currentThread, transaction);
        } else {
            throw new NotSupportedException("Nested transactions are not supported");
        }

    }

    @Override
    public void commit() throws RollbackException, HeuristicMixedException,
            HeuristicRollbackException, SecurityException,
            IllegalStateException, SystemException {
        Transaction transaction = getTransaction();
        try {
            transaction.commit();
        } finally {
            Thread currentThread = Thread.currentThread();
            threadTransactionMap.remove(currentThread);
        }
    }

    @Override
    public int getStatus() throws SystemException {
        int result;
        Transaction transaction = getTransaction();
        if (transaction != null) {
            result = transaction.getStatus();
        } else {
            result = Status.STATUS_NO_TRANSACTION;
        }
        return result;
    }

    @Override
    public Transaction getTransaction() throws SystemException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resume(Transaction transaction)
            throws InvalidTransactionException, IllegalStateException,
            SystemException {
    }

    @Override
    public void rollback() throws IllegalStateException, SecurityException,
            SystemException {
    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {
    }

    @Override
    public void setTransactionTimeout(int timeout) throws SystemException {
    }

    @Override
    public Transaction suspend() throws SystemException {
        throw new UnsupportedOperationException();
    }
}
