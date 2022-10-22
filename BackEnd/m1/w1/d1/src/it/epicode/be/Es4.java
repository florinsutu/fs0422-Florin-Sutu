package it.epicode.be;

public class Es4 {

	public static void main(String[] args) {
		System.out.println(perimetroRettangolo(4.33,7.89));
		System.out.println(pariDispari(55));
		System.out.println(areaTriangolo(4f,5f,6f));
	}
	
	public static double perimetroRettangolo(double l1, double l2) {
		return (l1*2)+(l2*2);
	}
	
	public static int pariDispari(int n) {
		return n%2;
	}
	
    public static float areaTriangolo(float l1, float l2, float l3) {
        float p = (l1 + l2 + l3)/2;
        return (float) Math.sqrt(p * (p - l1) * (p - l2) * (p - l3));
    }

}
