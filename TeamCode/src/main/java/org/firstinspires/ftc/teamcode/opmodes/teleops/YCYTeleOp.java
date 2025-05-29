package org.firstinspires.ftc.teamcode.opmodes.teleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.drive.TeleopMovement;
import org.firstinspires.ftc.teamcode.utils.FunctionalButton;

@TeleOp(name = "YCYTeleOp")
public class YCYTeleOp extends CommandOpMode {
    public Follower follower;
    private final Pose startPose = new Pose(0, 0, 0);
    private GamepadEx gamepadEx1;

    @Override
    public void initialize() {
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        gamepadEx1 = new GamepadEx(gamepad1);

        schedule(new TeleopMovement(follower, false, gamepadEx1));

        new FunctionalButton(() -> gamepadEx1.getButton(GamepadKeys.Button.RIGHT_STICK_BUTTON))
                .whenPressed(new InstantCommand(() -> follower.setStartingPose(startPose)));
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
    }
}
