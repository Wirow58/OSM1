import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

public final class ArgumentsControlLib {


    public static boolean isPatientOK(AppView mView) {
        boolean c1, c3, c5, c7, c8;
        boolean c2 = false;
        boolean c4 = false;
        boolean c6 = false;

        c1 = !(mView.getImie().isEmpty());
        if (c1) {
            Pattern p = Pattern.compile("[0-9]");
            Matcher m = p.matcher(mView.getImie());
            c2 = !(m.find());
        }

        c3 = !(mView.getNazwisko().isEmpty());
        if (c3) {
            Pattern p = Pattern.compile("[0-9]");
            Matcher m = p.matcher(mView.getNazwisko());
            c4 = !(m.find());
        }

        c5 = !(mView.getPesel().isEmpty());
        if (c5) {
            if (mView.getPesel().length() == 11) {
                c6 = true;
            }
        }

        c7 = StringUtils.isNumeric(mView.getPesel());

        c8 = !(mView.radioTest());

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        return c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8;
    }


    public static boolean isExaminationOK(AppView mView) {
        boolean c1 = StringUtils.isNumeric(mView.getHDL());
        boolean c2 = StringUtils.isNumeric(mView.getLDL());
        boolean c3 = StringUtils.isNumeric(mView.getGlicerydy());
        return c1 && c2 && c3;
    }


    public static boolean checkPesel(String pesel, List<Patient> patientList) {
        for (int i = 0; i < patientList.size(); i++) {
            if (pesel.equals(patientList.get(i).getPesel())) {
                return false;
            }
        }
        return true;
    }


    public static void examinationRange(AppView mView) {
        String HDL = "HDL jest w normie";
        String LDL = "LDL jest w normie";
        String Glicerydy = "Poziom glicerydów jest w normie";

        if (Integer.parseInt(mView.getHDL()) < 30) {
            HDL = "HDL jest za niskie";
        } else {
            if (Integer.parseInt(mView.getHDL()) > 60) {
                HDL = "HDL jest za wysokie";
            }
        }

        if (Integer.parseInt(mView.getLDL()) < 80) {
            LDL = "LDL jest za niskie";
        } else {
            if (Integer.parseInt(mView.getLDL()) > 120) {
                LDL = "LDL jest za wysokie";
            }
        }

        if (Integer.parseInt(mView.getGlicerydy()) < 10) {
            Glicerydy = "Poziom glicerydów jest za niski";
        } else {
            if (Integer.parseInt(mView.getGlicerydy()) > 20) {
                Glicerydy = "Poziom glicerydów jest za wysoki";
            }
        }

        JOptionPane.showMessageDialog(null,
                "Twoje badanie : \n" + HDL + ",\n" + LDL + ",\n" + Glicerydy,
                "Wyniki badań",
                JOptionPane.INFORMATION_MESSAGE);

    }


}
