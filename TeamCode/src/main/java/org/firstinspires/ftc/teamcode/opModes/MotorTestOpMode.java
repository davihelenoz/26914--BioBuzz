package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import kotlinx.coroutines.internal.OpDescriptor;
@TeleOp
public class MotorTestOpMode extends OpMode {
    DcMotorEx m0;
    DcMotorEx m1;
    DcMotorEx m2;
    DcMotorEx m3;
    @Override
    public void init() {
        m0 = hardwareMap.get(DcMotorEx.class, "m0");
        m1 = hardwareMap.get(DcMotorEx.class, "m1");
        m2 = hardwareMap.get(DcMotorEx.class, "m2");
        m3 = hardwareMap.get(DcMotorEx.class, "m3");

    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            m0.setPower(1);
        }
        else {
            m0.setPower(0);
        }

        if (gamepad1.b) {
            m1.setPower(1);
        }
        else {
            m1.setPower(0);
        }
        if (gamepad1.x) {
            m2.setPower(1);
        }
        else {
            m2.setPower(0);
        }
        if (gamepad1.y) {
            m3.setPower(1);
        }
        else {
            m3.setPower(0);
        }

    }
}
