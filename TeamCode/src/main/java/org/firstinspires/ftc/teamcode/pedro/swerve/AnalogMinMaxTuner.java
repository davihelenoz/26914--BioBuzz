package org.firstinspires.ftc.teamcode.pedro.swerve;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import java.util.List;

/**
 * Tuning OpMode to get the min and max encoder values for swerve pods
 */
@TeleOp(name = "Analog Min Max Tuner")
public class AnalogMinMaxTuner extends OpMode {

    // Encoders
    public String[] encoderNames = {
            Constants.leftFront.servoEncoderName.get(),
            Constants.rightFront.servoEncoderName.get(),
            Constants.leftBack.servoEncoderName.get(),
            Constants.rightBack.servoEncoderName.get()
    };

    public AnalogInput[] encoders = new AnalogInput[encoderNames.length];

    // CRServos
    public String[] servoNames = {
            Constants.leftFront.servoName.get(),
            Constants.rightFront.servoName.get(),
            Constants.leftBack.servoName.get(),
            Constants.rightBack.servoName.get()
    };

    public CRServo[] servos = new CRServo[servoNames.length];

    // Min / Max
    public double[] minVoltages = new double[encoderNames.length];
    public double[] maxVoltages = new double[encoderNames.length];

    public List<LynxModule> lynxModules;

    @Override
    public void init_loop() {
        telemetry.addLine(
                "Press START. Then, spin each pod slowly for 4 to 5 full rotations.\n" +
                        "The OpMode will keep track of the min and max voltages seen so far."
        );
        telemetry.update();
    }

    @Override
    public void init() {

        // Bulk caching
        lynxModules = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : lynxModules) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        // Encoders
        for (int i = 0; i < encoders.length; i++) {
            encoders[i] = hardwareMap.get(
                    AnalogInput.class,
                    encoderNames[i]
            );

            minVoltages[i] = 5.0;
            maxVoltages[i] = 0.0;
        }

        // CRServos
        for (int i = 0; i < servos.length; i++) {
            servos[i] = hardwareMap.get(
                    CRServo.class,
                    servoNames[i]
            );
        }
    }

    @Override
    public void loop() {

        // Limpa cache
        for (LynxModule hub : lynxModules) {
            hub.clearBulkCache();
        }

        // Mantém todos os CRServos em 0
        for (CRServo servo : servos) {
            servo.setPower(0.0);
        }

        telemetry.addLine(
                "Spin each pod slowly for 4 to 5 full rotations.\n" +
                        "The OpMode will keep track of the min and max voltages seen so far.\n"
        );

        // Leitura dos encoders
        for (int i = 0; i < encoders.length; i++) {

            double currentVoltage = encoders[i].getVoltage();

            minVoltages[i] = Math.min(
                    minVoltages[i],
                    currentVoltage
            );

            maxVoltages[i] = Math.max(
                    maxVoltages[i],
                    currentVoltage
            );

            telemetry.addData(
                    encoderNames[i] + " min value:",
                    "%.4f V",
                    minVoltages[i]
            );

            telemetry.addData(
                    encoderNames[i] + " max value:",
                    "%.4f V",
                    maxVoltages[i]
            );

            telemetry.addData(
                    encoderNames[i] + " current:",
                    "%.4f V",
                    currentVoltage

            );

            telemetry.addLine("");
        }

        telemetry.update();
    }
}