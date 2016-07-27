/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package creature.cells;

/**
 *
 * @author bhargav
 */
 public class ReproductionCell extends Cell{

        public ReproductionCell(int maxHP, int maxStore, int energy, int x, int y) {
            
            super(1, 2, 3, x, y);
        }

//        @Override
//        public void doAction() {
//
//            //creature.use energy -USER VAR-
//            //make a baby
//            //make creature check for 2 reproductive cells that are near each other
//        }
    }