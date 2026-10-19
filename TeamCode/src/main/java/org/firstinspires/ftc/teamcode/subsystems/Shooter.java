package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import dev.easyfamily.wintechlib.hardware.Motors.EasyMotor;
import dev.easyfamily.wintechlib.hardware.Motors.EasyMotorType;

public class Shooter {
    private EasyMotor shooter;
    public Shooter(HardwareMap hardwareMap) {
        shooter = new EasyMotor(hardwareMap, "Shooter", EasyMotorType.GOBILDA_6000).setDeadband(0.01);
    }
    public void ShooterRPM(double rpm) {
        shooter.setRPM(rpm);
    }

}