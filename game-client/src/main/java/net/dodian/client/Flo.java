package net.dodian.client;

public final class Flo {

    public boolean occlude;
    public int anInt394;
    public int anInt395;
    public int anInt396;
    public int anInt397;
    public int anInt398;

    public int anInt399;
    // Constructor, getters, setters, and other methods...
    public static Flo[] underlays;
    public static Flo[] overlays;

        public Flo() {
            occlude = true; // or false, depending on the desired default value
        }


    public boolean isOcclude() {
        return occlude;
    }
    int rgb = 0;
    int texture = -1;

    // HSL values
    private int hue, saturation, lumiance;
    private int anotherRgb = -1; // Assuming -1 indicates 'not set'

    // Constructor
    private Flo(boolean occlude) {
    }

    public static void unpackConfig(StreamLoader streamLoader) {
        Stream stream = new Stream(streamLoader.getDataForName("flo.dat"));
        underlays = unpackFlo(stream, true);
        overlays = unpackFlo(stream, false);
    }

    private static Flo[] unpackFlo(Stream stream, boolean isUnderlay) {
        int count = stream.readUnsignedWord();
        System.out.println("Loaded: " + count + " " + (isUnderlay ? "underlays" : "overlays"));
        Flo[] floors = new Flo[count];
        for (int i = 0; i < count; i++) {
            floors[i] = new Flo();
            floors[i].readValues(stream, isUnderlay);
        }
        return floors;
    }

    private void readValues(Stream stream, boolean isUnderlay) {
        int opcode;
        while ((opcode = stream.readUnsignedByte()) != 0) {
            switch (opcode) {
                case 1:
                    this.rgb = stream.read3Bytes();
                    break;
                case 2:
                    if (!isUnderlay) this.texture = stream.readUnsignedByte();
                    break;
                case 5:
                    if (!isUnderlay) ;
                    break;
                case 7:
                    if (!isUnderlay) this.anotherRgb = stream.read3Bytes();
                    break;
                default:
                    System.out.println("Error unrecognised " + (isUnderlay ? "underlay" : "overlay") + " code: " + opcode);
            }
        }
        generateHsl(isUnderlay);
    }

    private void generateHsl(boolean isUnderlay) {
        int targetRgb = (anotherRgb != -1) ? anotherRgb : this.rgb;
        convertRgbToHsl(targetRgb);
    }

    private void convertRgbToHsl(int rgbValue) {
        double r = (rgbValue >> 16 & 0xff) / 255.0;
        double g = (rgbValue >> 8 & 0xff) / 255.0;
        double b = (rgbValue & 0xff) / 255.0;

        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double delta = max - min;

        if (delta == 0) {
            hue = 0;
        } else if (max == r) {
            hue = (int) (60 * (((g - b) / delta) % 6));
        } else if (max == g) {
            hue = (int) (60 * (((b - r) / delta) + 2));
        } else {
            hue = (int) (60 * (((r - g) / delta) + 4));
        }

        lumiance = (int) ((max + min) / 2 * 100);

        if (delta == 0) {
            saturation = 0;
        } else {
            saturation = (int) (delta / (1 - Math.abs(2 * lumiance / 100.0 - 1)) * 100);
        }

        hue = Math.max(0, Math.min(hue, 360));
        saturation = Math.max(0, Math.min(saturation, 100));
        lumiance = Math.max(0, Math.min(lumiance, 100));
    }

    // Utility method for HSL conversion, simplified and corrected
    private static int calculateHslValue(int i, int j, int k) {
        j = j > 179 ? j / 2 : j;
        j = j > 192 ? j / 2 : j;
        j = j > 217 ? j / 2 : j;
        j = j > 243 ? j / 2 : j;
        return (i / 4 << 10) + (j / 32 << 7) + k / 2;
    }
}