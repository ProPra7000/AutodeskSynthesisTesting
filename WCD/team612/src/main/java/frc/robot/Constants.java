package frc.robot;

public final class Constants {

    public static boolean ENABLE_ARCADE = true;
    public static boolean SCORE_AUTO = true;

    // PCM Ports
    public static final int PCM_1 = 0;
    public static final int PCM_2 = 1;

    /* -------- Drivetrain Subsystem -------- */

    // Drivetrain talon ports
    public static int SPARK_FR_DRIVE = 2;//59
    public static int SPARK_FL_DRIVE = 12;
    public static int SPARK_BR_DRIVE = 3;
    public static int SPARK_BL_DRIVE = 1;

    // Forward and reverse channel for drive double solenoid
    public static int[] ULTRASONIC_DRIVE = {2,4};

    // Ping and echo channel port for drive ultrasonic
    public static int[] SOLENOID_DRIVE = {0,1};//1

   
}