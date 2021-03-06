/*
 * This file is part of Event Implementation Generator, licensed under the MIT License (MIT).
 *
 * Copyright (c) GerdaMC <https://www.fotontv.fun>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fun.fotontv.eventgerdagen.factory;

import spoon.reflect.declaration.CtType;

import java.util.Objects;

/**
 * Creates event implementations by generating the necessary event class
 * and event factory at runtime.
 */
public class ClassGeneratorProvider {

    //private final GeneratorUtils.LocalClassLoader classLoader = new GeneratorUtils.LocalClassLoader(ClassGeneratorProvider.class.getClassLoader());
    public final ClassGenerator builder = new ClassGenerator();
    private final String targetPackage;

    /**
     * Create a new instance.
     *
     * @param targetPackage The target package to place generated event classes in
     */
    public ClassGeneratorProvider(final String targetPackage) {
        Objects.requireNonNull(targetPackage, "targetPackage");
        this.targetPackage = targetPackage;
    }

    /**
     * Get the canonical name used for a generated event class.
     *
     * @param clazz The class
     * @param classifier The classifier
     * @return Canonical name
     */
    protected String getClassName(CtType<?> clazz, final String classifier) {
        String name = clazz.getSimpleName();
        while (clazz.getDeclaringType() != null) {
            clazz = clazz.getDeclaringType();
            name = clazz.getSimpleName() + "$" + name;
        }
        return this.targetPackage + "." + name + "$" + classifier;
    }
}
