/*-
 * ---license-start
 * keycloak-config-cli
 * ---
 * Copyright (C) 2017 - 2021 adorsys GmbH & Co. KG @ https://adorsys.com
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ---license-end
 */

package de.adorsys.keycloak.config.service;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.adorsys.keycloak.config.AbstractImportIT;
import de.adorsys.keycloak.config.exception.ImportProcessingException;
import de.adorsys.keycloak.config.exception.InvalidImportException;
import de.adorsys.keycloak.config.exception.KeycloakRepositoryException;
import de.adorsys.keycloak.config.util.VersionUtil;
import java.io.IOException;
import java.util.Map;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.keycloak.representations.idm.RealmRepresentation;

@SuppressWarnings({"java:S5961", "java:S5976"})
class ImportRealmWithPasskeysIT extends AbstractImportIT {
    private static final String REALM_NAME = "simple-passkey";

    ImportRealmWithPasskeysIT() {
        this.resourcePath = "import-files/simple-realm";
    }

    @Test
     void shouldImportRealmWithPasskeyParameters() throws Exception {
        doImport("12_update_simple-realm_with_passkeys-settings.json");
        RealmRepresentation updatedRealm = keycloakProvider.getInstance().realm(REALM_NAME).toRepresentation();

        assertThat(updatedRealm.getWebAuthnPolicyPasswordlessPasskeysEnabled(), is(true));
        assertThat(updatedRealm.getWebAuthnPolicyPasswordlessRpEntityName(), is("Keycloak"));
    }

}
