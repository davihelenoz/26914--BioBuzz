package org.firstinspires.ftc.teamcode.opModes;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
//commit
@Configurable
@TeleOp(name = "TeleOp")
public class teleOP extends OpMode {

    private Follower follower;
    private Intake Inteiki;

    private Shooter shuter;


    @Override
    public void init() {

        follower = Constants.create(hardwareMap);
        Inteiki = new Intake(hardwareMap, gamepad1);
        shuter = new Shooter(hardwareMap);
    }

    @Override
    public void loop() {
        follower.manual(
                -gamepad1.left_stick_y * 0.3,
                -gamepad1.left_stick_x * 0.3,
                gamepad1.right_stick_x * 0.3
        );
        if(gamepad1.circle) {
            shuter.ShooterRPM(3000);
        }
        Inteiki.teleop();

        telemetry.addData("Motor 1 current", Inteiki.getmAhINT1());
        telemetry.addData("Motor 2 current", Inteiki.getmAhINT2());

        telemetry.update();
        follower.update();

    }
}