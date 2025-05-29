package org.firstinspires.ftc.teamcode.opmodes.teleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.drive.Drive;
import org.firstinspires.ftc.teamcode.subsystems.drive.constants.FConstants;
import org.firstinspires.ftc.teamcode.subsystems.drive.constants.LConstants;
import org.firstinspires.ftc.teamcode.utils.FunctionalButton;

@TeleOp(name = "YCYTeleOp")
public class YCYTeleOp extends CommandOpMode {
    private Drive drive;
    private final Pose startPose = new Pose(0, 0, 0);
    private GamepadEx gamepadEx1;

    @Override
    public void initialize() {
        drive = new Drive(hardwareMap, startPose);
        gamepadEx1 = new GamepadEx(gamepad1);

        new FunctionalButton(() -> Math.abs(gamepadEx1.getLeftX()) != 0 ||
                Math.abs(gamepadEx1.getLeftY()) != 0)
                .whenPressed(
                        drive.teleopDriveCommand(
                                gamepadEx1.getLeftX(),
                                gamepadEx1.getLeftY(),
                                gamepadEx1.getRightX()
                        )
                );

        new FunctionalButton(() -> gamepadEx1.getButton(GamepadKeys.Button.RIGHT_STICK_BUTTON))
                .whenPressed(
                        drive.resetPoseCommand(startPose)
                );
    }

    @Override
    public void run() {
        drive.follower.startTeleopDrive();
        CommandScheduler.getInstance().run();
    }
}
