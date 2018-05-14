package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueueAndDequeue() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(1, arb.dequeue());
        assertEquals(2, arb.dequeue());
        arb.enqueue(4);
        assertEquals(3, arb.dequeue());
        assertEquals(1, arb.fillCount());
    }

    @Test
    public void testUnderFlowAndOverflow() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(10);
        try {
            arb.dequeue();
        } catch (RuntimeException e) {
            assertEquals(0, arb.fillCount());
        }

        try {
            arb.enqueue(1);
            arb.enqueue(2);
            arb.enqueue(3);
            arb.enqueue(4);
            arb.enqueue(5);
            arb.enqueue(6);
            arb.enqueue(7);
            arb.enqueue(8);
            arb.enqueue(9);
            arb.enqueue(10);
            arb.enqueue(11);
        } catch (RuntimeException e) {
            assertEquals(10, arb.fillCount());
        }
    }
} 
