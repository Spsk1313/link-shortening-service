CREATE TABLE links (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   owner_id BIGINT NOT NULL,
   destination_url VARCHAR(2048) NOT NULL,
   short_code VARCHAR(32) NOT NULL,
   link_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
   expires_at TIMESTAMPTZ,
   deleted_at TIMESTAMPTZ,
   created_at TIMESTAMPTZ NOT NULL,
   updated_at TIMESTAMPTZ NOT NULL,
   version BIGINT NOT NULL DEFAULT 0,

   CONSTRAINT chk_positive_owner_id
       CHECK (owner_id > 0),

   CONSTRAINT uq_links_short_code
       UNIQUE (short_code),

   CONSTRAINT chk_alphanumeric_shortcode
       CHECK (short_code ~ '^[A-Za-z0-9]{4,32}$'),

   CONSTRAINT chk_destination_url_not_blank
       CHECK (BTRIM(destination_url) <> ''),

   CONSTRAINT chk_valid_link_status
       CHECK (
           link_status IN (
                           'ACTIVE',
                           'OWNER_DISABLED',
                           'MODERATION_DISABLED'
               )
           ),

   CONSTRAINT chk_updated_after_equal_created
       CHECK (updated_at >= created_at),

   CONSTRAINT chk_deleted_after_created
       CHECK (
           deleted_at IS NULL
               OR deleted_at >= created_at
           ),

   CONSTRAINT chk_expires_after_created
       CHECK (
           expires_at IS NULL
               OR expires_at > created_at
           ),

   CONSTRAINT chk_version_greater_than_or_equal_zero
       CHECK (version >= 0)
);

CREATE INDEX idx_links_owner_created_at
    ON links (owner_id, created_at DESC);