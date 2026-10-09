package org.firstinspires.ftc.teamcode.opModes;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Configurable
@TeleOp(name = "TeleOp")
public class teleOP extends OpMode {
    private boolean IsINTbothOn = false;

    private boolean Trava = true;

    private boolean Dumper = false;

    private Follower follower;
    private Intake Inteiki;

    @Override
    public void init() {

        follower = Constants.create(hardwareMap);
        Inteiki = new Intake(hardwareMap);
    }

    @Override
    public void loop() {
        follower.manual(
                -gamepad1.left_stick_y * 0.3,
                -gamepad1.left_stick_x * 0.3,
                gamepad1.right_stick_x * 0.3
        );
        if(gamepad1.squareWasPressed()) {
            if (IsINTbothOn) {
                IsINTbothOn = false;
            }
            else {
                IsINTbothOn = true;
            }
        }
        if (IsINTbothOn) {
            Inteiki.AllOn(1);
        } else {
            Inteiki.InOn(gamepad1.left_trigger);
            Inteiki.BackOn(gamepad1.right_trigger);
        }
        if(gamepad1.triangleWasPressed()) {
            if (Trava) {
                Trava = false;
            }
            else {
                Trava = true;
            }
        }
        if(gamepad1.crossWasPressed()) {
            if (Dumper) {
                Dumper = false;
            }
            else {
                Dumper = true;
            }
        }
        if (Trava) {
            Inteiki.Trava(true);
        } else {
            Inteiki.Trava(false);
        }
        if (Dumper) {
            Inteiki.Dumper(true);
        } else {
            Inteiki.Dumper(false);
        }
        follower.update();

    }
}