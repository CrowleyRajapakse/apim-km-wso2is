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
 * Interface to access and manage invalid token information.
 */
public interface InvalidTokenPersistenceService {

    /**
     * Check if a token is marked as invalid due to direct token revocations.
     *
     * @param token       The token to check.
     * @param consumerKey The consumer key associated with the token.
     * @return {@code true} if the token is marked as invalid, {@code false} otherwise.
     * @throws IdentityOAuth2Exception If an error occurs during the check.
     */
    boolean isInvalidToken(String token, String consumerKey) throws IdentityOAuth2Exception;

    /**
     * Mark a token as invalid with its expiry time during generation.
     *
     * @param token       The token to mark as invalid.
     * @param consumerKey The consumer key associated with the token.
     * @param expiryTime  The expiry time set for the token during generation.
     * @throws IdentityOAuth2Exception If an error occurs while marking the token as invalid.
     */
    void addInvalidToken(String token, String consumerKey, Long expiryTime) throws IdentityOAuth2Exception;

    /**
     * Check if a token has been revoked as a result of any changes in the consumer app associated with the token
     * after the token's issuance timestamp.
     *
     * @param consumerKey     The consumer key for which token was issued.
     * @param tokenIssuedTime The timestamp at which token was issued.
     * @return {@code true} if the token has been revoked for the specified consumer app after the given timestamp,
     * {@code false} otherwise.
     * @throws IdentityOAuth2Exception If an error occurs during the token revocation check.
     */
    boolean isTokenRevokedForConsumerKey(String consumerKey, Date tokenIssuedTime) throws IdentityOAuth2Exception;

    /**
     * Check if a token has been revoked as a result of any changes in the subject principle/entity associated with
     * the token after the token's issuance timestamp.
     *
     * @param entityId        The entity/subject principle ID for which the token was issued for.
     * @param tokenIssuedTime The timestamp at which token was issued.
     * @return {@code true} if the token has been revoked for the specified entity after the given timestamp,
     * {@code false} otherwise.
     * @throws IdentityOAuth2Exception If an error occurs during the token revocation check.
     */
    boolean isTokenRevokedForSubjectEntity(String entityId, Date tokenIssuedTime) throws IdentityOAuth2Exception;

    /**
     * Revoke access tokens based on a user event.
     *
     * @param subjectId           The subject identifier for the user.
     * @param subjectIdType       The type of subject identifier.
     * @param revocationTime      The time of the revocation event.
     * @param organization        The organization associated with the revocation.
     * @param retryAttemptCounter The retry attempt counter.
     * @throws IdentityOAuth2Exception If an error occurs during access token revocation.
     */
    void revokeTokensByUserEvent(String subjectId, String subjectIdType, long revocationTime, String organization,
                                 int retryAttemptCounter) throws IdentityOAuth2Exception;

    /**
     * Revoke access tokens based on a consumer key event.
     *
     * @param consumerKey         The consumer key for which access tokens should be revoked.
     * @param revocationTime      The time of the revocation event.
     * @param organization        The organization associated with the revocation.
     * @param retryAttemptCounter The retry attempt counter.
     * @throws IdentityOAuth2Exception If an error occurs during access token revocation.
     */
    void revokeTokensByConsumerKeyEvent(String consumerKey, long revocationTime, String organization,
                                        int retryAttemptCounter) throws IdentityOAuth2Exception;
}
