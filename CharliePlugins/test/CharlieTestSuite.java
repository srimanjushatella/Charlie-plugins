/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import charlie.bs.section1.*;
import charlie.bs.section2.*;
import charlie.bs.section3.*;
import charlie.bs.section4.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author TSM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    Test00_12_2.class,
    Test01_12_2.class,
    Test00_12_7.class,
    Test01_12_7.class,
    
    Test00_5_2.class,
    Test01_5_2.class,
    Test00_5_7.class,
    Test01_5_7.class,
    
    Test00_A2_2.class,
    Test01_A2_2.class,
    Test00_A2_7.class,
    Test01_A2_7.class,
    
    Test00_22_2.class,
    Test01_22_2.class,
    Test00_22_7.class,
    Test01_22_7.class,
})
public class CharlieTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
