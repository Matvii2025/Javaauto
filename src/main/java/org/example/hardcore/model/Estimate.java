package org.example.hardcore.model;

import java.util.Objects;

public class Estimate {
    public final int instances;
    public final String operatingSystem;
    public final String machineClass;
    public final String machineType;
    public final String gpuType;
    public final int gpuCount;
    public final String ssd;
    public final String datacenter;
    public final int committedUseYears;

    private Estimate(Builder b) {
        this.instances = b.instances;
        this.operatingSystem = b.operatingSystem;
        this.machineClass = b.machineClass;
        this.machineType = b.machineType;
        this.gpuType = b.gpuType;
        this.gpuCount = b.gpuCount;
        this.ssd = b.ssd;
        this.datacenter = b.datacenter;
        this.committedUseYears = b.committedUseYears;
    }

    public static class Builder {
        private int instances = 4;
        private String operatingSystem = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOS";
        private String machineClass = "Regular";
        private String machineType = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
        private String gpuType = "NVIDIA Tesla V100";
        private int gpuCount = 1;
        private String ssd = "2x375 GB";
        private String datacenter = "Frankfurt (europe-west3)";
        private int committedUseYears = 1;

        public Builder instances(int v) { this.instances = v; return this; }
        public Builder os(String v) { this.operatingSystem = v; return this; }
        public Builder machineClass(String v) { this.machineClass = v; return this; }
        public Builder machineType(String v) { this.machineType = v; return this; }
        public Builder gpu(String type, int count) { this.gpuType = type; this.gpuCount = count; return this; }
        public Builder ssd(String v) { this.ssd = v; return this; }
        public Builder datacenter(String v) { this.datacenter = v; return this; }
        public Builder committedUseYears(int y) { this.committedUseYears = y; return this; }
        public Estimate build() { return new Estimate(this); }
    }

    @Override public String toString() {
        return "Estimate{" +
                "instances=" + instances +
                ", OS='" + operatingSystem + '\'' +
                ", class='" + machineClass + '\'' +
                ", type='" + machineType + '\'' +
                ", gpu='" + gpuType + " x" + gpuCount + '\'' +
                ", ssd='" + ssd + '\'' +
                ", dc='" + datacenter + '\'' +
                ", commit=" + committedUseYears + "y" +
                '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estimate e = (Estimate) o;
        return instances == e.instances &&
                gpuCount == e.gpuCount &&
                committedUseYears == e.committedUseYears &&
                Objects.equals(operatingSystem, e.operatingSystem) &&
                Objects.equals(machineClass, e.machineClass) &&
                Objects.equals(machineType, e.machineType) &&
                Objects.equals(gpuType, e.gpuType) &&
                Objects.equals(ssd, e.ssd) &&
                Objects.equals(datacenter, e.datacenter);
    }

    @Override public int hashCode() {
        return Objects.hash(instances, operatingSystem, machineClass, machineType, gpuType, gpuCount, ssd, datacenter, committedUseYears);
    }
}