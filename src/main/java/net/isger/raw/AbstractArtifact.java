package net.isger.raw;

public abstract class AbstractArtifact implements Artifact {

    private Raw raw;

    protected AbstractArtifact(Raw raw) {
        this.raw = raw;
    }

    public Raw getRaw() {
        return raw;
    }

}
