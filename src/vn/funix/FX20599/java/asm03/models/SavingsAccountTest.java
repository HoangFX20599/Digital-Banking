package vn.funix.FX20599.java.asm03.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SavingsAccountTest {
    private SavingsAccount savingAccount;

    @Before
    public void setUp() {
        savingAccount = new SavingsAccount("123456", 1_000_000);
    }

    @Test
    public void testWithdraw() throws Exception {
        savingAccount.withdraw(400_000);
        assertEquals(600_000, savingAccount.getBalance(), 0);
    }

    @Test
    public void testIsAccepted() throws Exception {
        assertTrue(savingAccount.isAccepted(5000));
//        assertFalse(savingAccount.isAccepted(100000)); // Amount exceeds limit
    }
}
