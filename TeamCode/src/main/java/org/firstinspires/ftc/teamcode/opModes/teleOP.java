package org.firstinspires.ftc.teamcode.opModes;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Configurable
@TeleOp(name = "TeleOp")
public class teleOP extends OpMode {

    private Follower follower;
    public static double turn = 0.4;
    public static double forward = 0.0;
    public static double strafe = 0;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
    }

    @Override
    public void loop() {
        follower.manual(
                -forward,
                strafe,
                turn
        );

        follower.update();
    }
}