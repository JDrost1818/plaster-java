package github.jdrost1818.plaster.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FileInformation {

    private List<Field> fields;

}
