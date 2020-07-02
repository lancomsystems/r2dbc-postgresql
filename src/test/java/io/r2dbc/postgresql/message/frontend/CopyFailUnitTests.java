/*
 * Copyright 2017-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.r2dbc.postgresql.message.frontend;

import org.junit.jupiter.api.Test;

import static io.netty.util.CharsetUtil.UTF_8;
import static io.r2dbc.postgresql.message.frontend.FrontendMessageAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Unit tests for {@link CopyFail}.
 */
final class CopyFailUnitTests {

    @Test
    void constructorNoMessage() {
        assertThatIllegalArgumentException().isThrownBy(() -> new CopyFail(null))
            .withMessage("message must not be null");
    }

    @Test
    void encode() {
        assertThat(new CopyFail("test-message")).encoded()
            .isDeferred()
            .isEncodedAs(buffer -> {
                buffer
                    .writeByte('f')
                    .writeInt(17);

                buffer.writeCharSequence("test-message", UTF_8);
                buffer.writeByte(0);

                return buffer;
            });
    }

}