package tests;

import base.conf.BaseTest;
import base.page.BbcPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class BbcTests extends BaseTest {

    private BbcPage bbcPage;

    private SoftAssert softAssert = new SoftAssert();
    private static final int VALUE1 = 1;
    private static final int VALUE2 = 2;

    private static final String STRFULL1 = "Test string 1";
    private static final String STRFULL2 = "Test string 2";

    private static final String STR1 = "Use testNG Assertion";
    private static final String STR2 = "test1";

    private static final int[] ARR1 = {1,2,3};
    private static final int[] ARR2 = {1,2,3,4};

    private static final String[] STRARR1 = {"1", "2"};
    private static final String[] STRARR2 = {"1", "2", "3"};

    @BeforeTest
    public void getUrl () {
        driver.get("https://www.bbc.com/");
        bbcPage = new BbcPage(driver, wait);
    }

    @Test (priority = 0)
    public void checkSearchInputTest() {
        bbcPage
                .textEnter("TEST");
        Assert.assertEquals("https://www.bbc.co.uk/search?q=TEST", bbcPage.returnCurrentUrl());
    }

    @Test (priority = 1)
    public void checkIsDisplayedSearchInputTest() {
        Assert.assertTrue(bbcPage.isDisplayedSearchInput());
    }

    @Test (priority = 2)
    public void checkIsEnabledSearchInputTest() {
        Assert.assertTrue(bbcPage.isEnabledSearchInput());
    }

    @Test (priority = 3)
    public void checkNavigationButtonTest() {
        bbcPage
            .clickOnNavigationWeatherButton();
        Assert.assertEquals("https://www.bbc.com/weather", bbcPage.returnCurrentUrl());
    }

    @Test (priority = 4)
    public void checkDifferentAssertion () {
        softAssert.assertEquals(VALUE1, VALUE2, "Two number not equals");
        softAssert.assertEquals(STRFULL1, STRFULL2, "Strings are not identical");
        softAssert.assertTrue(STR1.contains(STR2), "No match found for strings");
        softAssert.assertEquals(ARR1, ARR2, "Arrays of numbers are not identical");

        softAssert.assertEquals(STRARR1, STRARR2, "Arrays of string are not identical");
        softAssert.assertEquals(Arrays.toString(STRARR1), Arrays.toString(STRARR2), "Arrays of numbers are not identical");
        softAssert.assertAll();
    }
}
