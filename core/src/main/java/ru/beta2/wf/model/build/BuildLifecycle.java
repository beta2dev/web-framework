package ru.beta2.wf.model.build;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 19:54
 */
public interface BuildLifecycle
{

    BuildStage getNextStage(BuildContext ctx);

}
