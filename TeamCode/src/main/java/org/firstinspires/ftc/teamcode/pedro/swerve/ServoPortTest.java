package org.firstinspires.ftc.teamcode.pedro.swerve;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@Configurable
@TeleOp(name = "Servo Port Test")
public class ServoPortTest extends LinearOpMode {

    private CRServo lf;
    private CRServo rf;
    private CRServo lb;
    private CRServo rb;

    // TESTE AQUI
    public static double LF_POWER = 0.0;
    public static double RF_POWER = 0.3;
    public static double LB_POWER = 0.0;
    public static double RB_POWER = 0.0;

    @Override
    public void runOpMode() {

        lf = hardwareMap.get(CRServo.class, "lfTurn");
        rf = hardwareMap.get(CRServo.class, "rfTurn");
        lb = hardwareMap.get(CRServo.class, "lbTurn");
        rb = hardwareMap.get(CRServo.class, "rbTurn");

        waitForStart();

        while (opModeIsActive()) {

            lf.setPower(LF_POWER);
            rf.setPower(RF_POWER);
            lb.setPower(LB_POWER);
            rb.setPower(RB_POWER);

            telemetry.addData("LF", LF_POWER);
            telemetry.addData("RF", RF_POWER);
            telemetry.addData("LB", LB_POWER);
            telemetry.addData("RB", RB_POWER);
            telemetry.update();
        }
    }
}