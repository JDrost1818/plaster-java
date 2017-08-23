package github.jdrost1818.plaster.service.task.generate;

import github.jdrost1818.plaster.data.Setting;
import github.jdrost1818.plaster.data.TemplateType;
import github.jdrost1818.plaster.service.ServiceProvider;
import github.jdrost1818.plaster.service.task.PlasterTaskId;
import github.jdrost1818.plaster.service.task.util.CheckFileExists;
import github.jdrost1818.plaster.service.task.util.CheckSetting;

public class ControllerTestGenerate extends GenerateTask {

    public ControllerTestGenerate() {
        super(
                "Could not generate controller tests",
                PlasterTaskId.CONTROLLER_TEST,
                ServiceProvider.getControllerTestTemplateService(),
                new CheckSetting(
                        Setting.IS_REST_DOCUMENTATION_TESTING_ENABLED,
                        new CheckFileExists(
                                TemplateType.IT_DOC_PARENT,
                                new ControllerITDocGenerate(),
                                new ITDocParentGenerate(new ControllerITDocGenerate())
                        ),
                        new ControllerItGenerate())
        );
    }

}
