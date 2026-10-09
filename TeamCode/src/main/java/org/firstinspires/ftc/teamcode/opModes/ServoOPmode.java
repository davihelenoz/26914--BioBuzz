package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoOPmode extends OpMode {
    Servo Servo;
    //CRServo Servo1;
    @Override
    public void init() {

        Servo = hardwareMap.get(Servo.class, "INTtrava");
    }


    @Override
    public void loop() {
        telemetry.addData("left stick position", gamepad1.left_stick_y);
        telemetry.addData("right stick position", gamepad1.right_stick_y);
        Servo.setPosition(gamepad1.left_stick_y);
    }
}
