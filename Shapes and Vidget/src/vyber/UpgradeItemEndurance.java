package vyber;

import postava.SeznamHrdinu;

import java.util.Scanner;

public class UpgradeItemEndurance extends UpgradeItem{
    public UpgradeItemEndurance(String description, SeznamHrdinu hrdina) {
        super(description, hrdina);
    }

    @Override
    public boolean execute() {
        Scanner sc = new Scanner(System.in);
        int skillpointy = hrdina.getHrdinove().get(0).getSkillpoint();

        System.out.println("Aktuálně máš " + skillpointy + " volných skillpointů.");
        int sprOdpoved = 0;
        while (sprOdpoved == 0) {
            System.out.println("Kolik skillpointů chceš použít ke zvíšení odolnosti.");
            int kolik = sc.nextInt();
            if (kolik > 0 && kolik <= skillpointy) {
                for (int i = 0; i < kolik; i++) {
                    double zviseniOdolnosti = hrdina.getHrdinove().get(0).getOdolnost();
                    zviseniOdolnosti = zviseniOdolnosti  + 0.5;
                    hrdina.getHrdinove().get(0).setOdolnost(zviseniOdolnosti);
                    skillpointy--;
                    hrdina.getHrdinove().get(0).setSkillpoint(skillpointy);
                }
                sprOdpoved++;
            }
        }
        return false;
    }
}