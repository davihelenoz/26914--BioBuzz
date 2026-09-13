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

    // =========================
    // MIN / MAX DOS ENCODERS
    // =========================

    private static final double LF_MIN_VOLTAGE = 0.0230;
    private static final double LF_MAX_VOLTAGE = 3.2010;

    private static final double RF_MIN_VOLTAGE = 0.0200;
    private static final double RF_MAX_VOLTAGE = 3.2000;

    private static final double LB_MIN_VOLTAGE = 0.0080;
    private static final double LB_MAX_VOLTAGE = 3.1960;

    private static final double RB_MIN_VOLTAGE = 0.0240;
    private static final double RB_MAX_VOLTAGE = 3.2050;

    // =========================
    // OFFSETS EM GRAUS
    // =========================

    private static double LF_OFFSET = 127.7;
    private static double RF_OFFSET = 297.2;
    private static double LB_OFFSET = 332.2;
    private static double RB_OFFSET = 337.5;

    @Override
    public void runOpMode() {

        // Encoders
        lfEncoder = hardwareMap.get(
                AnalogInput.class, "lfTurnEncoder");

        rfEncoder = hardwareMap.get(
                AnalogInput.class, "rfTurnEncoder");

        lbEncoder = hardwareMap.get(
                AnalogInput.class, "lbTurnEncoder");

        rbEncoder = hardwareMap.get(
                AnalogInput.class, "rbTurnEncoder");

        // CRServos
        lfServo = hardwareMap.get(
                CRServo.class, "lfTurn");

        rfServo = hardwareMap.get(
                CRServo.class, "rfTurn");

        lbServo = hardwareMap.get(
                CRServo.class, "lbTurn");

        rbServo = hardwareMap.get(
                CRServo.class, "rbTurn");

        waitForStart();

        while (opModeIsActive()) {

            // Mantém os CRServos parados
            lfServo.setPower(0.0);
            rfServo.setPower(0.0);
            lbServo.setPower(0.0);
            rbServo.setPower(0.0);

            // =========================
            // LEITURA DAS VOLTAGENS
            // =========================

            double lfVoltage = lfEncoder.getVoltage();
            double rfVoltage = rfEncoder.getVoltage();
            double lbVoltage = lbEncoder.getVoltage();
            double rbVoltage = rbEncoder.getVoltage();

            // =========================
            // CONVERSÃO PARA ÂNGULO
            // =========================

            double lfAngle = voltageToAngle(
                    lfVoltage,
                    LF_MIN_VOLTAGE,
                    LF_MAX_VOLTAGE
            );

            double rfAngle = voltageToAngle(
                    rfVoltage,
                    RF_MIN_VOLTAGE,
                    RF_MAX_VOLTAGE
            );

            double lbAngle = voltageToAngle(
                    lbVoltage,
                    LB_MIN_VOLTAGE,
                    LB_MAX_VOLTAGE
            );

            double rbAngle = voltageToAngle(
                    rbVoltage,
                    RB_MIN_VOLTAGE,
                    RB_MAX_VOLTAGE
            );

            // =========================
            // APLICAÇÃO DOS OFFSETS
            // =========================

            double lfCorrected =
                    normalizeAngle(lfAngle - LF_OFFSET);

            double rfCorrected =
                    normalizeAngle(rfAngle - RF_OFFSET);

            double lbCorrected =
                    normalizeAngle(lbAngle - LB_OFFSET);

            double rbCorrected =
                    normalizeAngle(rbAngle - RB_OFFSET);

            // =========================
            // TELEMETRIA
            // =========================

            telemetry.addLine("===== SWERVE OFFSET TEST =====");
            telemetry.addLine("");

            telemetry.addData(
                    "LF",
                    "V: %.3f | Raw: %.1f° | Offset: %.1f° | Corrected: %.1f°",
                    lfVoltage,
                    lfAngle,
                    LF_OFFSET,
                    lfCorrected
            );

            telemetry.addData(
                    "RF",
                    "V: %.3f | Raw: %.1f° | Offset: %.1f° | Corrected: %.1f°",
                    rfVoltage,
                    rfAngle,
                    RF_OFFSET,
                    rfCorrected
            );

            telemetry.addData(
                    "LB",
                    "V: %.3f | Raw: %.1f° | Offset: %.1f° | Corrected: %.1f°",
                    lbVoltage,
                    lbAngle,
                    LB_OFFSET,
                    lbCorrected
            );

            telemetry.addData(
                    "RB",
                    "V: %.3f | Raw: %.1f° | Offset: %.1f° | Corrected: %.1f°",
                    rbVoltage,
                    rbAngle,
                    RB_OFFSET,
                    rbCorrected
            );

            telemetry.update();
        }
    }

    /**
     * Converte a tensão do encoder para 0-360 graus
     * usando MIN e MAX específicos de cada pod.
     */
    private double voltageToAngle(
            double voltage,
            double minVoltage,
            double maxVoltage) {

        double angle =
                (voltage - minVoltage)
                        / (maxVoltage - minVoltage)
                        * 360.0;

        return normalizeAngle(angle);
    }

    /**
     * Mantém o ângulo entre 0 e 360 graus.
     */
    private double normalizeAngle(double angle) {

        angle %= 360.0;

        if (angle < 0) {
            angle += 360.0;
        }

        return angle;
    }
}