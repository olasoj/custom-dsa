package com.dsa.misc.datetime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Duration;
import java.time.Instant;

public final class ComputeRunningTImeResult {
    private final Instant startInstant;
    private final Duration runningDuration;
    private final Instant endInstant;
    private final Duration graceDayDuration;

    public ComputeRunningTImeResult(ComputeOverdraftRunningTImeResultBuilder computeOverdraftRunningTImeResultBuilder) {
//        Assert.notNull(computeOverdraftRunningTImeResultBuilder, "Only non-null ComputeOverdraftRunningTImeResultBuilder instances are permitted");

//        Assert.notNull(computeOverdraftRunningTImeResultBuilder.endInstant, "End instant cannot be null");
        this.endInstant = computeOverdraftRunningTImeResultBuilder.endInstant;

//        Assert.notNull(computeOverdraftRunningTImeResultBuilder.startInstant, "Start instant cannot be null");
        this.startInstant = computeOverdraftRunningTImeResultBuilder.startInstant;

//        Assert.notNull(computeOverdraftRunningTImeResultBuilder.duration, "Running duration cannot be null");
        this.runningDuration = computeOverdraftRunningTImeResultBuilder.duration;

//        Assert.notNull(computeOverdraftRunningTImeResultBuilder.graceDuration, "Grace duration cannot be null");
        this.graceDayDuration = computeOverdraftRunningTImeResultBuilder.graceDuration;
    }

    public static ComputeOverdraftRunningTImeResultBuilder builder() {
        return new ComputeOverdraftRunningTImeResultBuilder();
    }

    public Instant endInstant() {
        return endInstant;
    }

    public Instant startInstant() {
        return startInstant;
    }

    public Duration duration() {
        return runningDuration;
    }

    public Duration graceDayDuration() {
        return graceDayDuration;
    }

    public Duration effectiveSessionDuration() {
        Duration durationBetweenStartInstantAndNow = Duration.between(startInstant, Instant.now());
        Duration effectiveSessionDuration = durationBetweenStartInstantAndNow.minus(graceDayDuration);
        return (effectiveSessionDuration.isNegative()) ? Duration.ofSeconds(1) : effectiveSessionDuration;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.endInstant)
                .append(this.startInstant)
                .append(this.runningDuration)
                .append(this.graceDayDuration)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof ComputeRunningTImeResult))
            return false;

        final ComputeRunningTImeResult otherComputeRunningTImeResult = (ComputeRunningTImeResult) obj;
        return new EqualsBuilder()
                .append(this.endInstant, otherComputeRunningTImeResult.endInstant)
                .append(this.startInstant, otherComputeRunningTImeResult.startInstant)
                .append(this.runningDuration, otherComputeRunningTImeResult.runningDuration)
                .append(this.graceDayDuration, otherComputeRunningTImeResult.graceDayDuration)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("endInstant", this.endInstant)
                .append("startInstant", this.startInstant)
                .append("duration", this.runningDuration)
                .append("graceDayDuration", this.graceDayDuration)
                .toString();
    }

    public static class ComputeOverdraftRunningTImeResultBuilder {
        private Instant endInstant;
        private Instant startInstant;
        private Duration duration;
        private Duration graceDuration;


        public ComputeOverdraftRunningTImeResultBuilder endInstant(Instant endInstant) {
            this.endInstant = endInstant;
            return this;
        }

        public ComputeOverdraftRunningTImeResultBuilder duration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public ComputeOverdraftRunningTImeResultBuilder graceDuration(Duration graceDayDuration) {
            this.graceDuration = graceDayDuration;
            return this;
        }

        public ComputeOverdraftRunningTImeResultBuilder startInstant(Instant startInstant) {
            this.startInstant = startInstant;
            return this;
        }

        public ComputeRunningTImeResult build() {
            return new ComputeRunningTImeResult(this);
        }
    }

}
