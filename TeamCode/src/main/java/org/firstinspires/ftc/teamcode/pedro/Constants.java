    package org.firstinspires.ftc.teamcode.pedro;

    import com.bylazar.configurables.annotations.Configurable;
    import com.pedropathing.algorithm.Foresight;
    import com.pedropathing.algorithm.ForesightConfig;
    import com.pedropathing.controllers.Controller;
    import com.pedropathing.follower.Follower;
    import com.pedropathing.math.Matrix;
    import com.pedropathing.math.Vector2D;
    import com.pedropathing.revhub.drivetrains.CoaxialPod;
    import com.pedropathing.revhub.drivetrains.CoaxialPodConfig;
    import com.pedropathing.revhub.drivetrains.Mecanum;
    import com.pedropathing.revhub.drivetrains.MecanumConfig;
    import com.pedropathing.revhub.drivetrains.Swerve;
    import com.pedropathing.revhub.drivetrains.SwerveConfig;
    import com.pedropathing.revhub.localizers.PinpointConfig;
    import com.pedropathing.revhub.localizers.PinpointLocalizer;
    import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.HardwareMap;

    @Configurable
    public class Constants {

        public static double kPLF = 0.2;
        public static double kILF = 0;
        public static double kDLF = 0.0008;

        public static double kPLB = 0.2;
        public static double kILB = 0;
        public static double kDLB = 0.0008;

        public static double kPRF = 0.2;
        public static double kIRF = 0;
        public static double kDRF = 0.0008;

        public static double kPRB = 0.2;
        public static double kIRB = 0;
        public static double kDRB = 0.0008;

        public static double kV = 0.063;
        public static double offsetPod = 4.866;

        public static MecanumConfig driveConfig = new MecanumConfig(
                c -> {
                    c.frontLeftName.set("m0");
                    c.backLeftName.set("m1");
                    c.frontRightName.set("m2");
                    c.backRightName.set("m3");

                    c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                    c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                    c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                    c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
                }
        );

        // CONFIGURAÇÃO DO PINPOINT
        public static PinpointConfig localizerConfig = new PinpointConfig(
                c -> {
                    c.name.set("pinpoint");
                    c.xPodOffset.set(2.187);
                    c.yPodOffset.set(-4.572);
                    c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
                    c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
                }
        );

        // CONFIGURAÇÃO DO FORESIGHT
        public static ForesightConfig foresightConfig = new ForesightConfig(
                c -> {
                    Controller primaryTranslationalForward = Controller.proportional(0.3);
                    Controller secondaryTranslationalForward = Controller.proportional(0.1);
                    Controller primaryTranslationalLateral = Controller.proportional(0.3);
                    Controller secondaryTranslationalLateral = Controller.proportional(0.1);
                    c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                    c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));
                    c.coast.set(Controller.proportionalFeedforward(0.010978350889324107));
                    c.brake.set(Controller.proportionalFeedforward(0.008731598255925491));
                    c.headingFeedback.set(Controller.proportional(5.258721785960744));
                    c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05642143125655298, 0.0063829525363003695));
                    c.linearBrakeCoefficients.set(Matrix.diag(0.10605894992901523, 0.08719146175596092));
                    c.quadraticBrakeCoefficients.set(Matrix.diag(0.0014663966976606565, 0.0013837064502458813));
                    c.maxAchievableForwardVelocity.set(72.72923108818539);
                    c.maxAchievableStrafeVelocity.set(52.34323936525474);
                    c.naturalForwardDeceleration.set(85.01144677379789);
                    c.naturalStrafeDeceleration.set(104.49787535782846);
                }
        );

        // CRIAÇÃO DO FOLLOWER
        public static Follower create(HardwareMap h) {
            return new Follower(
                    new PinpointLocalizer(h, localizerConfig),
                    new Mecanum(h, driveConfig),
                    new Foresight(foresightConfig)
            );
        }
    }
