/*
 * Copyright (c) 2002-2022 Manorrock.com. All Rights Reserved.
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
package jakarta.transaction.tests;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 * The JUnit tests for the Transaction class.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
class TransactionalTest {
    
    /**
     * Test dontRollbackOn method.
     */
    @Test
    void testDontRollbackOn() {
        Transactional transactional = new TestTransactional();
        assertNull(transactional.dontRollbackOn());
    }

    /**
     * Test rollbackOn method.
     */
    @Test
    void testRollbackOn() {
        Transactional transactional = new TestTransactional();
        assertNull(transactional.rollbackOn());
    }

    /**
     * Test value method.
     */
    @Test
    void testValue() {
        Transactional transactional = new TestTransactional();
        assertEquals(TxType.MANDATORY, transactional.value());
    }
    
    /**
     * Test annotationType method.
     */
    @Test
    void testAnnotationType() {
        Transactional transactional = new TestTransactional();
        assertNull(transactional.annotationType());
    }
}
