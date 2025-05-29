package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.drive.constants.FConstants;
import org.firstinspires.ftc.teamcode.subsystems.drive.constants.LConstants;

public class Drive extends SubsystemBase {
    public Follower follower;

    public Drive(HardwareMap hardwareMap, Pose startPose) {
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startPose);
    }

    public Command teleopDriveCommand(double ly, double lx, double rx) {
        return new InstantCommand(() -> follower
                .setTeleOpMovementVectors(-ly, -lx, -rx, true))
                .andThen(new InstantCommand(follower::update));
    }

    public Command resetPoseCommand(Pose startPose) {
        return new InstantCommand(() -> follower.setStartingPose(startPose));
    }
}
