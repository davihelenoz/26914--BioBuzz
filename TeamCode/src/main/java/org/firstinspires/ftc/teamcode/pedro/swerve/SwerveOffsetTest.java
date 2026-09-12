package org.firstinspires.ftc.teamcode.pedro.swerve;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "Swerve Offset Test")
public class SwerveOffsetTest extends LinearOpMode {

    private AnalogInput lfEncoder;
    private AnalogInput rfEncoder;
    private AnalogInput lbEncoder;
    private AnalogInput rbEncoder;

    private CRServo lfServo;
    private CRServo rfServo;
    private CRServo lbServo;
    private CRServo rbServo;

    private static final double MIN_VOLTAGE = 0.0;
    private static final double MAX_VOLTAGE = 3.3;

    private static double LF_OFFSET = 143.0;
    private static double RF_OFFSET = 288.2;
    private static double LB_OFFSET = 107.0;
    private static double RB_OFFSET = 331.2;

    @Override
    public void runOpMode() {

        lfEncoder = hardwareMap.get(AnalogInput.class, "lfTurnEncoder");
        rfEncoder = hardwareMap.get(AnalogInput.class, "rfTurnEncoder");
        lbEncoder = hardwareMap.get(AnalogInput.class, "lbTurnEncoder");
        rbEncoder = hardwareMap.get(AnalogInput.class, "rbTurnEncoder");

        lfServo = hardwareMap.get(CRServo.class, "lfTurn");
        rfServo = hardwareMap.get(CRServo.class, "rfTurn");
        lbServo = hardwareMap.get(CRServo.class, "lbTurn");
        rbServo = hardwareMap.get(CRServo.class, "rbTurn");

        waitForStart();

        while (opModeIsActive()) {

            // Ativa os CRServos sem movimentá-los
            lfServo.setPower(0.0);
            rfServo.setPower(0.0);
            lbServo.setPower(0.0);
            rbServo.setPower(0.0);

            double lfVoltage = lfEncoder.getVoltage();
            double rfVoltage = rfEncoder.getVoltage();
            double lbVoltage = lbEncoder.getVoltage();
            double rbVoltage = rbEncoder.getVoltage();

            double lfAngle = voltageToAngle(lfVoltage);
            double rfAngle = voltageToAngle(rfVoltage);
            double lbAngle = voltageToAngle(lbVoltage);
            double rbAngle = voltageToAngle(rbVoltage);

            double lfCorrected = normalizeAngle(lfAngle - LF_OFFSET);
            double rfCorrected = normalizeAngle(rfAngle - RF_OFFSET);
            double lbCorrected = normalizeAngle(lbAngle - LB_OFFSET);
            double rbCorrected = normalizeAngle(rbAngle - RB_OFFSET);

            telemetry.addLine("===== SWERVE OFFSET TEST =====");

            telemetry.addData("LF",
                    "V: %.3f | Raw: %.1f° | Offset: %.1f° | Corrected: %.1f°",
                    lfVoltage, lfAngle, LF_OFFSET, lfCorrected);

            telemetry.addData("RF",
                    "V: %.3f | Raw: %.1f° | Offset: %.1f° | Corrected: %.1f°",
                    rfVoltage, rfAngle, RF_OFFSET, rfCorrected);

            telemetry.addData("LB",
                    "V: %.3f | Raw: %.1f° | Offset: %.1f° | Corrected: %.1f°",
                    lbVoltage, lbAngle, LB_OFFSET, lbCorrected);

            telemetry.addData("RB",
                    "V: %.3f | Raw: %.1f° | Offset: %.1f° | Corrected: %.1f°",
                    rbVoltage, rbAngle, RB_OFFSET, rbCorrected);

            telemetry.update();
        }
    }

    private double voltageToAngle(double voltage) {
        double angle = (voltage - MIN_VOLTAGE)
                / (MAX_VOLTAGE - MIN_VOLTAGE)
                * 360.0;

        return normalizeAngle(angle);
    }

    private double normalizeAngle(double angle) {
        angle %= 360.0;

        if (angle < 0) {
            angle += 360.0;
        }

        return angle;
    }
}