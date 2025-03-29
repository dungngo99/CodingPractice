package org.example.DynamicProgramming;

public class ClosestDessertCost {
    private int res = 0;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        // requirement: need 1 base / 1 or more toppings / each topping at most 2
        // Idea: after we fixed a base cost, link to combination: each topping has 3 choices (quantity = 0 or 1 or 2)
        // at each leaf, check the abs(curCost-target) vs. abs(res-target)
        res = baseCosts[0];
        for (int i=0; i<baseCosts.length; i++){
            findToppings(baseCosts[i], toppingCosts, 0, target);
        }
        return res;
    }

    public void findToppings(int curCost, int[] toppingCosts, int i, int target){
        if (i == toppingCosts.length){
            if (Math.abs(target-res) > Math.abs(target-curCost) || Math.abs(target-res) == Math.abs(target-curCost) && res > curCost){
                res = curCost;
            }
            return;
        }
        findToppings(curCost, toppingCosts, i+1, target);
        findToppings(curCost + toppingCosts[i], toppingCosts, i+1, target);
        findToppings(curCost + toppingCosts[i]*2, toppingCosts, i+1, target);
    }

    public static void main(String[] args) {
        ClosestDessertCost solution = new ClosestDessertCost();
        System.out.println(solution.closestCost(new int[]{1,7}, new int[]{3,4}, 10));
        System.out.println(solution.closestCost(new int[]{10}, new int[]{1}, 1));
        System.out.println(solution.closestCost(new int[]{4}, new int[]{9}, 9));
    }
}