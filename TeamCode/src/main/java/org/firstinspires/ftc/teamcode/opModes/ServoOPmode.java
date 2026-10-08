package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
@TeleOp
public class ServoOPmode extends OpMode {
    CRServo Servo;
    CRServo Servo1;
    @Override
    public void init() {

        Servo = hardwareMap.get(CRServo.class, "Servo");
        Servo1 = hardwareMap.get(CRServo.class, "Servo1");
    }


    @Override
    public void loop() {
        telemetry.addData("left stick position", gamepad1.left_stick_y);
        telemetry.addData("right stick position", gamepad1.right_stick_y);
        if (gamepad1.dpad_right) {
            Servo.setPower(0.55);
        }
        else if (gamepad1.dpad_left) {
            Servo.setPower(-0.55);
        }
        else if (gamepad1.dpad_up) {
            Servo.setPower(1);
        }
        else if (gamepad1.dpad_down) {
            Servo.setPower(-1);
        }
        else {
            Servo.setPower(gamepad1.left_stick_y);
        }
        // outro lado
        if (gamepad1.circle) {
            Servo1.setPower(0.55);
        }
        else if (gamepad1.square) {
            Servo1.setPower(-0.55);
        }
        else if (gamepad1.triangle) {
            Servo1.setPower(1);
        }
        else if (gamepad1.cross) {
            Servo1.setPower(-1);
        }
        else {
            Servo1.setPower(gamepad1.right_stick_y);
        }
    }
}
