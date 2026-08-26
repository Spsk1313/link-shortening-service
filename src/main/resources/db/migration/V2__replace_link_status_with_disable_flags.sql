ALTER TABLE links
    ADD COLUMN owner_disabled BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN moderation_disabled BOOLEAN NOT NULL DEFAULT FALSE;

UPDATE links
SET owner_disabled = TRUE
WHERE link_status = 'OWNER_DISABLED';

UPDATE links
SET moderation_disabled = TRUE
WHERE link_status = 'MODERATION_DISABLED';

ALTER TABLE links
    DROP CONSTRAINT chk_valid_link_status;

ALTER TABLE links
    DROP COLUMN link_status;