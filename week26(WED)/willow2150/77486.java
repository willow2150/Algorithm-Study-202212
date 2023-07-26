import java.util.HashMap;
import java.util.Map;

class Solution {
    static class Employee {
        Employee referral;
        int profit;
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        final int PRICE = 100;
        final char NONE = '-';

        Map<String, Employee> employees = new HashMap<>(enroll.length);
        int[] result = new int[enroll.length];

        for (int index = 0; index < enroll.length; index++) {
            Employee employee = new Employee();
            employees.put(enroll[index], employee);
            if (referral[index].charAt(0) == NONE) {
                continue;
            }
            employee.referral = employees.get(referral[index]);
        }

        for (int index = 0; index < seller.length; index++) {
            Employee employee = employees.get(seller[index]);
            int profit = amount[index] * PRICE;
            while (employee != null) {
                int nextProfit = profit / 10;
                if (nextProfit == 0) {
                    employee.profit += profit;
                    break;
                }
                employee.profit += profit - nextProfit;
                profit = nextProfit;
                employee = employee.referral;
            }
        }

        for (int index = 0; index < enroll.length; index++) {
            result[index] = employees.get(enroll[index]).profit;
        }

        return result;
    }
}
