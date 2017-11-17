package pl.mo.general;

import org.junit.Assert;
import org.junit.Test;

public class ReflectionHelperTest {

    @Test
    public void getCurrentMethodNameTest() {
        // when
        String thisMethodName = ReflectionHelper.getCurrentMethodName();
        String innerScopeMethodName = runTheHelperMethodForProvidingTheInnerScope();

        // then
        Assert.assertEquals("getCurrentMethodNameTest", thisMethodName);
        Assert.assertEquals("runTheHelperMethodForProvidingTheInnerScope", innerScopeMethodName);
        Assert.assertEquals("getCurrentMethodNameTest", ReflectionHelper.getCurrentMethodName());
    }

    private String runTheHelperMethodForProvidingTheInnerScope() {
        return ReflectionHelper.getCurrentMethodName();
    }

}
