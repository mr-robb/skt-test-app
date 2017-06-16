package nearsoft.skt.test;

import org.springframework.stereotype.Component;

/**
 * Created by rfonseca on 6/15/17.
 */
@Component
public interface ActionPerformer {

    void execute(int times);
}