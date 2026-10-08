package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
@TeleOp
public class Limelight extends OpMode {
    Limelight3A limelight;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // Isso define com que frequência pedimos dados ao Limelight (100 vezes por segundo)
        limelight.start(); // Isso diz ao Limelight para começar a procurar!
        limelight.pipelineSwitch(3); // Mudar para o pipeline número 3

    }

    @Override
    public void loop() {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            double tx = result.getTx(); // Quão longe à esquerda ou direita o alvo está (graus)
            double ty = result.getTy(); // Quão longe para cima ou para baixo o alvo está (graus)
            double ta = result.getTa(); // Quão grande o alvo parece (0%-100% da imagem)

            telemetry.addData("Alvo X", tx);
            telemetry.addData("Alvo Y", ty);
            telemetry.addData("Área do Alvo", ta);
        } else {
            telemetry.addData("Limelight", "Sem Alvos");
        }
        // Enviando números para Python
        double[] inputs = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};
        limelight.updatePythonInputs(inputs);

// Recebendo números do Python
        double[] pythonOutputs = result.getPythonOutput();
        if (pythonOutputs != null && pythonOutputs.length > 0) {
            double firstOutput = pythonOutputs[0];
            telemetry.addData("Saída Python:", firstOutput);
        }

    }
}
