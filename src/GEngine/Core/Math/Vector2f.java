/*
 * Copyright (c) 2014, Vladimir Moskalyuk 
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package GEngine.Core.Math;

/**
 *
 * @author Vladimir Moskalyuk 
 */
public class Vector2f {
    private float x;
    private float y;
    
    public Vector2f(float _x, float _y)
    {
        this.x = _x;
        this.y = _y;
    }
    
    public float distance(Vector2f r)
    {
        Vector2f temp = sub(r);
        temp.setX(temp.getX()*temp.getX());
        temp.setY(temp.getY()*temp.getY());
        return (float)Math.sqrt(temp.getX() + temp.getY());
    }
    
    public float length()
    {
            return (float)Math.sqrt(x * x + y * y);
    }
    
    public float max()
    {
            return Math.max(x, y);
    }

    public float dot(Vector2f r)
    {
            return x * r.getX() + y * r.getY();
    }

    public Vector2f normalized()
    {
            float length = length();

            return new Vector2f(x / length, y / length);
    }

    public float cross(Vector2f r)
    {
            return x * r.getY() - y * r.getX();
    }

    public Vector2f lerp(Vector2f dest, float lerpFactor)
    {
            return dest.sub(this).mul(lerpFactor).add(this);
    }

    public Vector2f rotate(float angle)
    {
            double rad = Math.toRadians(angle);
            double cos = Math.cos(rad);
            double sin = Math.sin(rad);

            return new Vector2f((float)(x * cos - y * sin),(float)(x * sin + y * cos));
    }

    public Vector2f add(Vector2f r)
    {
            return new Vector2f(x + r.getX(), y + r.getY());
    }

    public Vector2f add(float r)
    {
            return new Vector2f(x + r, y + r);
    }

    public Vector2f sub(Vector2f r)
    {
            return new Vector2f(x - r.getX(), y - r.getY());
    }

    public Vector2f sub(float r)
    {
            return new Vector2f(x - r, y - r);
    }

    public Vector2f mul(Vector2f r)
    {
            return new Vector2f(x * r.getX(), y * r.getY());
    }

    public Vector2f mul(float r)
    {
            return new Vector2f(x * r, y * r);
    }

    public Vector2f div(Vector2f r)
    {
            return new Vector2f(x / r.getX(), y / r.getY());
    }

    public Vector2f div(float r)
    {
            return new Vector2f(x / r, y / r);
    }

    public Vector2f abs()
    {
            return new Vector2f(Math.abs(x), Math.abs(y));
    }

    @Override
    public String toString()
    {
            return "(" + x + " " + y + ")";
    }

    public Vector2f set(float x, float y) { this.x = x; this.y = y; return this; }
    public Vector2f set(Vector2f r) { set(r.getX(), r.getY()); return this; }

    public float getX() 
    {
            return x;
    }

    public void setX(float x) 
    {
            this.x = x;
    }

    public float getY() 
    {
            return y;
    }

    public void setY(float y)
    {
            this.y = y;
    }

    public boolean equals(Vector2f r)
    {
            return x == r.getX() && y == r.getY();
    }
    
}
