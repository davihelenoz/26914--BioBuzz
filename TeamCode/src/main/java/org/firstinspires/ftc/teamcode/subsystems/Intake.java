package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
//commit
public class Intake {
    DcMotorEx INT1;
    DcMotorEx INT2;

    Servo Trava;

    Servo Dumper;

    private Gamepad gamepad1;

    private boolean T = true;

    private boolean D = false;

    private boolean Domper = false;

    private boolean BackA = true;

    private boolean timebool = true;
    public Intake(HardwareMap hardwareMap, Gamepad gamepad) {
        INT1 = hardwareMap.get(DcMotorEx.class, "INT1");
        INT2 = hardwareMap.get(DcMotorEx.class, "INT2");
        Trava = hardwareMap.get(Servo.class, "INTtrava");
        Dumper = hardwareMap.get(Servo.class, "ServoFlor");
        this.gamepad1 = gamepad;
    }
    public void AllOn(double vel) {
        INT1.setPower(-vel);
        INT2.setPower(-vel);
    }
    public void InOn(double vel) {INT1.setPower(-vel);}
    public void BackOn(double vel) {INT2.setPower(-vel);}

    public void Trava(boolean est){
        if (est) {
            Trava.setPosition(0.5);
        } else {
            Trava.setPosition(0);
        }
    }

    public void Dumper(boolean est){
        if (est) {
            Dumper.setPosition(0);
        } else {
            Dumper.setPosition(1);
        }
    }
    public double getmAhINT1() {
        return INT1.getCurrent(CurrentUnit.MILLIAMPS);
    }

    public double getmAhINT2() {
        return INT2.getCurrent(CurrentUnit.MILLIAMPS);
    }

    public void teleop() {
        if(gamepad1.square) {
            if (BackA) {
                AllOn(1);
            } else {
                BackOn(0);
                InOn(1);
            }
        } else {
            InOn(gamepad1.left_trigger);
            BackOn(gamepad1.right_trigger);
        }
        if(gamepad1.cross) {
            Trava(false);
            AllOn(1);
            BackA = true;
        } else {
            Trava(true);
        }
        if(gamepad1.triangleWasPressed()) {
            if (Domper) {
                Domper = false;
            } else {
                Domper = true;
            }
        }

        if (Domper == true) {
            Dumper(true);
        } else {
            Dumper(false);
        }
        if (timebool){
            timebool = false;
        } else if (getmAhINT2() > 5000) {
            BackA = false;
        }
    }
}
