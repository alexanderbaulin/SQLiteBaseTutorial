package com.example.alex.sqlitebasetutorial;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented testDataBase, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Base db;
    private Long itemId;

    @Before
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        db = new Base(appContext);
        assertEquals("com.example.alex.sqlitebasetutorial", appContext.getPackageName());
    }

    @Test
    public void testDataBase() {
        testDrop();
        testCreate();
        testInsert();
        testSelect();
    }

    private void testSelect() {
        db.select();
    }

    private void testDrop() {
        db.drop();
    }

    private void testCreate() {
        db.create();
    }

    private void testInsert() {
        itemId = db.insert(testData());
        assertNotEquals(itemId, Long.valueOf(-1));
    }

    private Data testData() {
        Data data = new Data();
        data.description = "description";
        data.checkedDays = new boolean[]{false, false, false, false, true, true, true};
        data.timeBegin = new int[] { 12, 20 };
        data.timeEnd = new int[] { 12, 20 };
        data.isActivated = true;
        data.isVibrationAllowed = true;
        return data;
    }


}
