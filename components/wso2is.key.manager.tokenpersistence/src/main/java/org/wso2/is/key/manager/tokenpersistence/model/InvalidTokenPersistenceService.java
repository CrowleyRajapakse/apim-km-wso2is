/*
 * Copyright (c) 2023, WSO2 LLC. (https://www.wso2.com)
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.is.key.manager.tokenpersistence.model;

import org.wso2.carbon.identity.oauth2.IdentityOAuth2Exception;

import java.util.Date;

/**
 * Interface to access invalid token information.
 */
public interface InvalidTokenPersistenceService {

    boolean isInvalidToken(String token, String type, String consumerKey) throws IdentityOAuth2Exception;

    void addInvalidToken(String token, String type, String consumerKey, Long expiryTime)
            throws IdentityOAuth2Exception;

    boolean isRevokedJWTConsumerKeyExist(String consumerKey, Date timeStamp) throws IdentityOAuth2Exception;
}
