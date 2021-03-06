package ru.beta2.wf.model.build;

import ru.beta2.wf.model.build.stages.*;

/**
 * User: Inc
 * Date: 14.11.2014
 * Time: 22:31
 */
public class DefaultBuildLifecycle implements BuildLifecycle
{

    private int stage = 0;

    @Override
    public BuildStage getNextStage(BuildContext ctx)
    {
        stage++;
        switch (stage) {
            case 1:
                return new BuildUpStage(ctx);
            case 2:
                return new GenerateIdStage(ctx);
            case 3:
                return new AssignRendererStage(ctx);
            case 4:
                return new VerifyStage(ctx);
            case 5:
                return new GatherCommandsStage(ctx);
        }
        return null;
    }
}
