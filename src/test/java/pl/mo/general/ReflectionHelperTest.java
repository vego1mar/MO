package pl.mo.general;

import org.junit.Assert;
import org.junit.Test;

public class ReflectionHelperTest {

    @Test
    public void shouldReturnMethodNamesFromScopesWithinItExecutes() {
        // when
        String thisMethodName = ReflectionHelper.getCurrentMethodName();
        String innerScopeMethodName = runTheHelperMethodForProvidingTheInnerScope();

        // then
        Assert.assertEquals("shouldReturnMethodNamesFromScopesWithinItExecutes", thisMethodName);
        Assert.assertEquals("runTheHelperMethodForProvidingTheInnerScope", innerScopeMethodName);
        Assert.assertEquals("shouldReturnMethodNamesFromScopesWithinItExecutes", ReflectionHelper.getCurrentMethodName());
    }

    private String runTheHelperMethodForProvidingTheInnerScope() {
        return ReflectionHelper.getCurrentMethodName();
    }

}
