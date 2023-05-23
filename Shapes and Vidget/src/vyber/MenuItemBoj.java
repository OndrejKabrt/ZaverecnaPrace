package vyber;

import postava.Postava;
import postava.SeznamHrdinu;
import postava.SeznamNepratel;

import java.util.Random;
import java.util.Scanner;

public class MenuItemBoj extends MenuItem{

    public MenuItemBoj(String description, SeznamHrdinu hrdinove, SeznamNepratel nepratele) {
        super(description, hrdinove, nepratele);
    }
    public static void souboj(Postava hrdina, Postava nepritel) throws Exception{
        Scanner sc = new Scanner(System.in);
        int hrdinaD = 1;
        int nepritelD = 1;
        System.out.println("Prvni zacina hrdina.");
        System.out.println();
        System.out.println("Hrdina " + hrdina);
        System.out.println("Nepritel " + nepritel);
        System.out.println();
        System.out.println("Po každém kole zmáčkněte ENTER");
        System.out.println("------------------------------------------------------------------------------------");
        sc.nextLine();

        while(hrdinaD ==1 && nepritelD ==1){

            if(hrdina.getZivoty() <= 0){
                System.out.println("Hrdina je mrtvý vyhrál nepřítel.");
                hrdinaD++;

            }else{

                System.out.println("Hrdina "+hrdina.getJmeno() +" má "+ hrdina.getZivoty()+" a ted utoci.");
                double dmgH = hrdina.utociNa(nepritel);
                double odecetOdNepr = nepritel.getZivoty() - dmgH;
                nepritel.setZivoty(odecetOdNepr);
                System.out.println("Hrdina "+ hrdina.getJmeno() +" dává "+ nepritel.getJmeno()+"ovi dmg " + dmgH);

                sc.nextLine();

                if(nepritel.getZivoty() <= 0){
                    System.out.println("Nepritel je mrtvý vyhrál hrdina.");
                    nepritelD++;
                    hrdina.vyhra(nepritel.getLevel());

                }else{

                    System.out.println("Nepritel "+nepritel.getJmeno() +" má "+ nepritel.getZivoty()+" a ted utoci.");
                    double dmgN = nepritel.utociNa(hrdina);

                    double odecrtOdHrd = hrdina.getZivoty() - dmgN;
                    hrdina.setZivoty(odecrtOdHrd);
                    System.out.println("Nepritel "+ nepritel.getJmeno() +" dává "+ hrdina.getJmeno()+"ovi dmg " + dmgN);
                    sc.nextLine();
                }
            }
        }
        System.out.println("Konec hry.");
    }

    @Override
    public boolean execute() {
        SeznamHrdinu sh = null;
        try {
            sh = SeznamHrdinu.nacti("postavy.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SeznamNepratel snp = null;
        try {
            snp = SeznamNepratel.nacti("nepratele.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Random rnd = new Random();

        try {
            souboj(sh.getHrdinove().get(0), snp.getNepratele().get(rnd.nextInt(6)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
