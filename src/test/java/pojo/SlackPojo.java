package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import pojo.SlackMessagePojo;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlackPojo {

    private boolean ok;
    private String ts;
    private SlackMessagePojo message;

}
