package github.jdrost1818.plaster.service.template;

import github.jdrost1818.plaster.data.Setting;
import github.jdrost1818.plaster.data.TemplateType;
import github.jdrost1818.plaster.domain.GenTypeModel;
import github.jdrost1818.plaster.domain.template.FlattenedField;
import github.jdrost1818.plaster.service.ConfigurationService;
import org.jtwig.JtwigModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ControllerTemplateServiceTest {

    @Mock
    private ConfigurationService configurationService;

    private ControllerTemplateService classUnderTest;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        this.classUnderTest = new ControllerTemplateService(configurationService);
    }

    @Test
    public void addTypeField() throws Exception {
        JtwigModel model = JtwigModel.newModel();

        GenTypeModel genTypeModel = new GenTypeModel("example_class", false);

        when(this.configurationService.get(Setting.APP_PATH)).thenReturn("/com/example/app");
        when(this.configurationService.get(Setting.REL_CONTROLLER_PACKAGE)).thenReturn("/controller");
        when(this.configurationService.get(Setting.SUB_DIR_PATH)).thenReturn("/somewhere");

        JtwigModel modifiedModel = this.classUnderTest.addTypeField(model, genTypeModel, TemplateType.CONTROLLER);

        FlattenedField x = (FlattenedField) modifiedModel.get("controllerField").get().getValue();

        assertThat(x.getClassName(), equalTo("ExampleClassController"));
        assertThat(x.getPackagePath(), equalTo("com.example.app.controller.somewhere"));
        assertThat(x.getVarName(), equalTo("exampleClassController"));
    }
}