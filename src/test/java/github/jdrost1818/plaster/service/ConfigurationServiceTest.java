package github.jdrost1818.plaster.service;

import github.jdrost1818.plaster.data.Setting;
import github.jdrost1818.plaster.exception.PlasterException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigurationServiceTest {

    ConfigurationService classUnderTest = new ConfigurationService();

    private String root;

    @Before
    public void setUp() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("testProject").getFile());
        this.root = file.getAbsolutePath();
    }

    @Test(expected = PlasterException.class)
    public void load_pom_does_not_exist() throws Exception {
        this.classUnderTest.load("thisDoesNotExist.xml");
    }

    @Test
    public void load() throws Exception {
        this.classUnderTest.load(root + "/root1");

        assertThat(this.classUnderTest.get(Setting.KEY), equalTo("id:int"));
        assertThat(this.classUnderTest.get(Setting.IS_LOMBOK_SUPPORTED), equalTo("true"));
        assertThat(this.classUnderTest.get(Setting.BASE_PATH), equalTo("src/main/java"));
        assertThat(this.classUnderTest.get(Setting.SUB_DIR_PATH), equalTo(""));
        assertThat(this.classUnderTest.get(Setting.REL_PATH), equalTo("src/main/java/com/example/app"));
        assertThat(this.classUnderTest.get(Setting.MAVEN_GROUP_ID), equalTo("com.example.app"));
        assertThat(this.classUnderTest.get(Setting.REL_MODEL_PACKAGE), equalTo("model"));
        assertThat(this.classUnderTest.get(Setting.REL_REPOSITORY_PACKAGE), equalTo("repository"));
        assertThat(this.classUnderTest.get(Setting.REL_SERVICE_PACKAGE), equalTo("service"));
        assertThat(this.classUnderTest.get(Setting.REL_CONTROLLER_PACKAGE), equalTo("controller"));
        assertThat(this.classUnderTest.get(Setting.SHOULD_USE_PRIMITIVES), equalTo("false"));
    }

}