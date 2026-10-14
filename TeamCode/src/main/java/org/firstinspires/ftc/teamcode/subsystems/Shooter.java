package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

public class Shooter {
    private static DcMotorEx ShooterMaster, ShooterSlave;
    //Coefficients
    public static double kS = 0.0;
    public static double kV = 0.0;
    public static double kP = 0.0;
    public static double kF = 0;
    public static double RPMTol = 0;
    boolean Activated = false;
    public static double targetRPM = 0;

    public static void init(HardwareMap hwdMap) {
        ShooterMaster = hwdMap.get(DcMotorEx.class, "Shooter");

        ShooterMaster.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public static double PID(double targetRpm, double battery, double currentEncoderTicks) {
        double currentRpm = (currentEncoderTicks * 60.0) / 28;
        double kF = targetRpm / (kV * battery);
        double feedforwardStatic = kS / battery;
        double error = targetRpm - currentRpm;
        double proportional = kP * error;
        double output = kF + proportional + feedforwardStatic;
        return Math.max(0, Math.min(1, output));
    }

    public static void setRPM(double rpm, HardwareMap hwMap) {
        VoltageSensor battery = hwMap.voltageSensor.iterator().next();
        targetRPM = rpm;
        double power = PID(targetRPM, battery.getVoltage(), ShooterMaster.getVelocity());
        ShooterMaster.setPower(power);
    }
    public static double getRPM(HardwareMap hwMap) {
        return (ShooterMaster.getVelocity() * 60.0) / 28;
    }
}